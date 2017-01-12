//
//  ViewController.swift
//  MindSweeperDemo
//
//  Created by minni on 21/12/16.
//  Copyright © 2016 minni. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    
    @IBOutlet weak var boardView: UIView!
    @IBOutlet weak var scoreLabel: UILabel!
    @IBOutlet weak var timeLabel: UILabel!
    
    var totalNumberOfOnes = 0
    var totalPressedOnes = 0
    let BOARD_SIZE:Int = 10
    var board:Board
    var squareButtons:[SquareButton] = []
    
    var score:Int = 0 {
        didSet {
            self.scoreLabel.text = "Score: \(score)"
            self.scoreLabel.sizeToFit()
        }
    }
    var timeTaken:Int = 0  {
        didSet {
            self.timeLabel.text = "Time: \(timeTaken)"
            self.timeLabel.sizeToFit()
        }
    }
    var oneSecondTimer:Timer?
    
    
    required init?(coder aDecoder: NSCoder)
    {
        self.board = Board(size: BOARD_SIZE)
        
        super.init(coder: aDecoder)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.initializeBoard()
        self.startNewGame()
        self.getTotalOnes()
        // Do any additional setup after loading the view, typically from a nib.
    }
    func initializeBoard() {
        for row in 0 ..< board.size {
            for col in 0 ..< board.size {
                
                let square = board.squares[row][col]
                
                let squareSize:CGFloat = self.boardView.frame.width / CGFloat(BOARD_SIZE)
                
                let squareButton = SquareButton(squareModel: square, squareSize: squareSize);
                squareButton.setTitle("", for: UIControlState())
                squareButton.titleLabel?.font = UIFont.boldSystemFont(ofSize: 8.0)
                squareButton.setTitleColor(UIColor.black, for: UIControlState())
                squareButton.backgroundColor = UIColor.lightGray
                squareButton.layer.borderColor = UIColor.white.cgColor
                squareButton.layer.borderWidth = 1
                squareButton.addTarget(self, action: #selector(ViewController.squareButtonPressed(_:)), for: .touchUpInside)
                self.boardView.addSubview(squareButton)
                
                self.squareButtons.append(squareButton)
                
            }
        }
        
    }
    func getTotalOnes()
    {
        for squareButton in self.squareButtons {
            
            //squareButton.setTitle("\(squareButton.getLabelText())", for: UIControlState())
            if squareButton.getLabelText() == "1"
            {
                totalNumberOfOnes = totalNumberOfOnes + 1
            }
            
        }
        print("totalNumberOfOnes ======", totalNumberOfOnes)
        
    }
    func resetBoard() {
        // resets the board with new mine locations & sets isRevealed to false for each square
        self.board.resetBoard()
        // iterates through each button and resets the text to the default value
        for squareButton in self.squareButtons {
            squareButton.setTitle("", for: UIControlState())
            
        }
    }
    
    func startNewGame() {
        //start new game
        self.resetBoard()
        self.timeTaken = 0
        self.score = 0
        self.oneSecondTimer = Timer.scheduledTimer(timeInterval: 1.0, target: self, selector: #selector(ViewController.oneSecond), userInfo: nil, repeats: true)
    }
    
    func oneSecond() {
        self.timeTaken += 1
    }
    
    //MARK: Button Actions
    
    @IBAction func refreshGamePressed() {
        print("new game")
        for squareButton in self.squareButtons {
            squareButton.isEnabled = true
            squareButton.backgroundColor = UIColor.lightGray
            squareButton.setImage(UIImage(named: ""), for: UIControlState())
        }
        
        self.startNewGame()
    }
    
    func squareButtonPressed(_ sender: SquareButton) {
        print("Pressed row:\(sender.square.row), col:\(sender.square.col)")
        
        
        if !sender.square.isRevealed {
            sender.square.isRevealed = true
            sender.backgroundColor = UIColor.green
            sender.setTitle("\(sender.getLabelText())", for: UIControlState())
            if !sender.square.isMineLocation {
               self.score += 1
            }
            totalPressedOnes = totalPressedOnes + 1
            
            if totalPressedOnes == totalNumberOfOnes
            {
                self.endCurrentGame()
                
                let alert = UIAlertController(title: "You won!", message: "You won the game.", preferredStyle: UIAlertControllerStyle.alert)
                alert.addAction(UIAlertAction(title: "OK", style: UIAlertActionStyle.default, handler: nil))
                self.present(alert, animated: true, completion: nil)
                
            }
        }
        
        if sender.square.isMineLocation {
            self.minePressed()
            sender.backgroundColor = UIColor.red
        }
        
    }
    
    func minePressed() {
        
        self.endCurrentGame()
        
        let alert = UIAlertController(title: "BOOM!", message: "You tapped on a Bomb.", preferredStyle: UIAlertControllerStyle.alert)
        alert.addAction(UIAlertAction(title: "OK", style: UIAlertActionStyle.default, handler: nil))
        self.present(alert, animated: true, completion: nil)
        
        for squareButton in self.squareButtons {
            
            if squareButton.getLabelText() == "1"
            {
                
            }
            else
            {
                squareButton.setImage(UIImage(named: "mine.jpeg"), for: UIControlState())
            }
            squareButton.isEnabled = false
        }
        
    }
    
    
    func endCurrentGame() {
        self.oneSecondTimer!.invalidate()
        self.oneSecondTimer = nil
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
}

