//
//  ViewController.swift
//  FreeFlowWithCollection
//
//  Created by minni on 09/12/16.
//  Copyright © 2016 minni. All rights reserved.
//

import UIKit
import CoreData

class ViewController: UIViewController, UICollectionViewDataSource, UICollectionViewDelegate , UIGestureRecognizerDelegate{
    
    @IBOutlet weak var playerLabel: UILabel!
    @IBOutlet weak var collectionView: UICollectionView?
    var indexPathB : NSIndexPath! = nil
    var storedIndexPath : NSIndexPath! = nil
    var gridSize : Int = 5
    var playerTag : Int = 1
    
    let reuseIdentifier = "cell" // also enter this string as the cell identifier in the storyboard
    var items = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25"]
    
    
    override func viewDidLoad() {
        
        
        super.viewDidLoad()
        playerLabel.text = "Player1 Turn"
        
//        self.savePersonTurn()
//        self.fetchPersonTurn()
        
        self.deleteAllData(entity: "PathLine")
        self.deleteAllData(entity: "PersonTurn")
       
        // Do any additional setup after loading the view, typically from a nib.
    }
  
    
    // MARK:- Using coredatabase to save game data
    
    func deleteAllData(entity: String)
    {
        let moc = DataController().managedObjectContext
        let fetchRequest = NSFetchRequest<NSFetchRequestResult>(entityName: entity)
        fetchRequest.returnsObjectsAsFaults = false
        
        do
        {
            let results = try moc.fetch(fetchRequest)
            for managedObject in results
            {
                let managedObjectData:NSManagedObject = managedObject as! NSManagedObject
                moc.delete(managedObjectData)
            }
           
        } catch let error as NSError {
            print("Detele all data in \(entity) error : \(error) \(error.userInfo)")
        }
        do {
            try moc.save()
        } catch {
            fatalError("Failure to save context: \(error)")
        }
    }
  
    func fetchLinePathData(linePoints:String) -> Bool
    {
        var findValue : Bool = false
        let moc = DataController().managedObjectContext
        let fetchRequest = NSFetchRequest<NSFetchRequestResult>(entityName: "PathLine")
       
        let fullStr = linePoints
        let fullStrArr = fullStr.characters.split{$0 == ","}.map(String.init)
        let oppositestring = fullStrArr[1] + "," + fullStrArr[0]
        
        do {
            let results = try moc.fetch(fetchRequest)
            let  PathLines = results as! [PathLine]
            
            for item in PathLines {
                print("db items are", item.pathLinePoints)
                if item.pathLinePoints == linePoints || item.pathLinePoints == oppositestring
                {
                    findValue = true
                    break
                }
            }
        } catch let error as NSError {
            print("Could not fetch \(error)")
        }
     
        return findValue
    }
 
    func savePersonLinePoints(linePoints:String) {
        
        let moc = DataController().managedObjectContext
    
        let entity = NSEntityDescription.insertNewObject(forEntityName: "PathLine", into: moc) as! PathLine
        
        // add our data
        entity.setValue(linePoints, forKey: "pathLinePoints")
       
        do {
            try moc.save()
        } catch {
            fatalError("Failure to save context: \(error)")
        }

    }
   
    // MARK:- Using coredatabase to save person's turn
    func fetchPersonTurn() {
        let moc = DataController().managedObjectContext
        let personFetch = NSFetchRequest<NSFetchRequestResult>(entityName: "PersonTurn")
        
        do {
            let fetchedPerson = try moc.fetch(personFetch) as! [PersonTurn]
            print(fetchedPerson.first?.person1turn)
            print(fetchedPerson.first?.person2turn)
            
        } catch {
            fatalError("Failed to fetch person: \(error)")
        }
    }
    
    func savePersonTurn() {
        
        // create an instance of our managedObjectContext
        let moc = DataController().managedObjectContext
        
        // we set up our entity by selecting the entity and context that we're targeting
        
        
        let entity = NSEntityDescription.insertNewObject(forEntityName: "PersonTurn", into: moc) as! PersonTurn
        
        // add our data
        entity.setValue(true, forKey: "person1turn")
        entity.setValue(false, forKey: "person2turn")
        
        // we save our entity
        do {
            try moc.save()
        } catch {
            fatalError("Failure to save context: \(error)")
        }
    }

    
    // tell the collection view how many cells to make
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return self.items.count
    }
    
    // make a cell for each cell index path
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        
        
        // get a reference to our storyboard cell
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: reuseIdentifier,for:indexPath) as! MyCollectionViewCell
        cell.myButton.tag = indexPath.row
        cell.myButton.setImage(UIImage(named: "dot.png"), for: UIControlState.normal)
        cell.myButton.addTarget(self, action: #selector(pressButton(button:)), for: .touchUpInside)
        return cell
        
    }
    func savePathAndChangePlayerTurn(fromIndexPath: NSIndexPath, toIndexPath: NSIndexPath, pathString: String)
    {
        if playerTag == 1
        {
            playerTag = 2
            playerLabel.text = "Player2 Turn"
            collectionView?.drawLineFrom(from: fromIndexPath, to: toIndexPath, lineWidth: 5, strokeColor: UIColor.red)
            
        }
        else
        {
            playerTag = 1
            playerLabel.text = "Player1 Turn"
            collectionView?.drawLineFrom(from: fromIndexPath, to: toIndexPath, lineWidth: 5, strokeColor: UIColor.blue)
            
        }
        
        
        self.savePersonLinePoints(linePoints: pathString)
    }
    func pressButton(button: UIButton) {
        
        
        let view = button.superview!
        let cell = view.superview as! MyCollectionViewCell
        let indexPath = collectionView?.indexPath(for: cell)
        print(indexPath?.row)
        
        button.setImage(UIImage(named: "dot-selected.png"), for: UIControlState.normal)
        indexPathB = indexPath as NSIndexPath!
        if indexPathB != nil
        {
            
            if storedIndexPath != nil
            {
                print(storedIndexPath.row)
                if storedIndexPath.row % gridSize == 0
                {
                    print("************************************************leftTapped ****************************************************")
                    print("nearestPoint", (storedIndexPath.row) + 1)
                    print("nearestPoint", (storedIndexPath.row) + gridSize)
                    print("nearestPoint", (storedIndexPath.row) - gridSize)
                    
                    if indexPathB.row == storedIndexPath.row + 1 || indexPathB.row == storedIndexPath.row + gridSize || indexPathB.row == storedIndexPath.row - gridSize
                    {
                        
                        //Save Path index to DB
                        let fromStr = String(storedIndexPath.row)
                        let toStr = String(indexPathB.row)
                        let pathstring = fromStr + "," + toStr
                        
                        if self.fetchLinePathData(linePoints: pathstring) == true
                        {
                            
                        }
                        else
                        {
                            savePathAndChangePlayerTurn(fromIndexPath: storedIndexPath, toIndexPath: indexPathB, pathString: pathstring)
                            
                            let storedCell = collectionView!.cellForItem(at: storedIndexPath as IndexPath) as! MyCollectionViewCell
                            storedCell.myButton.setImage(UIImage(named: "dot.png"), for: UIControlState.normal)
                            button.setImage(UIImage(named: "dot.png"), for: UIControlState.normal)
                        }
                     
                        storedIndexPath = nil
                        

                    }
                    else
                    {
                        
                        let storedCell = collectionView!.cellForItem(at: storedIndexPath as IndexPath) as! MyCollectionViewCell
                        storedCell.myButton.setImage(UIImage(named: "dot.png"), for: UIControlState.normal)
                        button.setImage(UIImage(named: "dot.png"), for: UIControlState.normal)
                        storedIndexPath = nil
                    }
                }
                else if (storedIndexPath.row + 1) % gridSize == 0
                {
                    print("************************************************rightTapped************************************************")
                    print("nearestPoint", (storedIndexPath.row) - 1)
                    print("nearestPoint", (storedIndexPath.row) + gridSize)
                    print("nearestPoint", (storedIndexPath.row) - gridSize)
                    
                    
                    if indexPathB.row == storedIndexPath.row - 1 || indexPathB.row == storedIndexPath.row + gridSize || indexPathB.row == storedIndexPath.row - gridSize
                    {
                        
                        //Save Path index to DB
                        let fromStr = String(storedIndexPath.row)
                        let toStr = String(indexPathB.row)
                        let pathstring = fromStr + "," + toStr
                        
                        if self.fetchLinePathData(linePoints: pathstring) == true
                        {
                            
                        }
                        else
                        {
                            savePathAndChangePlayerTurn(fromIndexPath: storedIndexPath, toIndexPath: indexPathB, pathString: pathstring)
                         
                            let storedCell = collectionView!.cellForItem(at: storedIndexPath as IndexPath) as! MyCollectionViewCell
                            storedCell.myButton.setImage(UIImage(named: "dot.png"), for: UIControlState.normal)
                            button.setImage(UIImage(named: "dot.png"), for: UIControlState.normal)
                        }
                        
                        storedIndexPath = nil
                        
                    }
                    else
                    {
                        
                        let storedCell = collectionView!.cellForItem(at: storedIndexPath as IndexPath) as! MyCollectionViewCell
                        storedCell.myButton.setImage(UIImage(named: "dot.png"), for: UIControlState.normal)
                        button.setImage(UIImage(named: "dot.png"), for: UIControlState.normal)
                        storedIndexPath = nil
                        
                    }
                    
                    
                }
                else
                {
                    print("************************************************middleTapped************************************************")
                    print("nearestPoint", (storedIndexPath.row) + 1)
                    print("nearestPoint", (storedIndexPath.row) - 1)
                    print("nearestPoint", (storedIndexPath.row) + gridSize)
                    print("nearestPoint", (storedIndexPath.row) - gridSize)
                    
                    if indexPathB.row == storedIndexPath.row - 1 || indexPathB.row == storedIndexPath.row + 1 || indexPathB.row == storedIndexPath.row + gridSize || indexPathB.row == storedIndexPath.row - gridSize
                    {
                        
                        //Save Path index to DB
                        let fromStr = String(storedIndexPath.row)
                        let toStr = String(indexPathB.row)
                        let pathstring = fromStr + "," + toStr
                        
                        if self.fetchLinePathData(linePoints: pathstring) == true
                        {
                            
                        }
                        else
                        {
                           savePathAndChangePlayerTurn(fromIndexPath: storedIndexPath, toIndexPath: indexPathB, pathString: pathstring)
                            
                            let storedCell = collectionView!.cellForItem(at: storedIndexPath as IndexPath) as! MyCollectionViewCell
                            storedCell.myButton.setImage(UIImage(named: "dot.png"), for: UIControlState.normal)
                            button.setImage(UIImage(named: "dot.png"), for: UIControlState.normal)
                        }
                        
                        storedIndexPath = nil

                       
                        
                    }
                    else
                    {
                        
                        let storedCell = collectionView!.cellForItem(at: storedIndexPath as IndexPath) as! MyCollectionViewCell
                        storedCell.myButton.setImage(UIImage(named: "dot.png"), for: UIControlState.normal)
                        button.setImage(UIImage(named: "dot.png"), for: UIControlState.normal)
                        storedIndexPath = nil
                        
                    }
                    
                }
            }
                
            else
            {
                storedIndexPath = indexPathB
            }
            
        }
    }
}

extension UICollectionView {
    
    
    func drawLineFrom(from: NSIndexPath, to: NSIndexPath, lineWidth: CGFloat = 5, strokeColor: UIColor = UIColor.red)
    {
       
        let fromPoint1 = cellForItem(at: from as IndexPath)?.center
        let toPoint1 = cellForItem(at: to as IndexPath)?.center
        guard
            
            let fromPoint : CGPoint = CGPoint(x: (fromPoint1?.x)!, y: (fromPoint1?.y)!),
            let toPoint : CGPoint = CGPoint(x: (toPoint1?.x)!, y: (toPoint1?.y)!)
            
            
            else {
                return
        }
        
        let path = UIBezierPath()
        path.move(to: convert(fromPoint, to: self))
        path.addLine(to: convert(toPoint, to: self))
        
        let layer = CAShapeLayer()
        layer.path = path.cgPath
        layer.lineWidth = lineWidth
        layer.strokeColor = strokeColor.cgColor
        
        self.layer.addSublayer(layer)
    }
}
