

import Foundation

class Board {
    
   
    let size:Int
    var squares:[[Square]] = [] // a 2d array of square cells, indexed by [row][column]
  
    
    init(size:Int) {
        self.size = size
        
        for row in 0 ..< size {
            var squareRow:[Square] = []
            for col in 0 ..< size {
                let square = Square(row: row, col: col)
                squareRow.append(square)
            }
            squares.append(squareRow)
        }
    }
    
    func resetBoard(){
        // assign mines to squares
        for row in 0 ..< size {
            for col in 0 ..< size {
                squares[row][col].isRevealed = false
                self.calculateIsMineLocationForSquare(squares[row][col])
            }
        }
        
        // count number of neighboring squares
        for row in 0 ..< size {
            for col in 0 ..< size {
                self.calculateNumNeighborMinesForSquare(squares[row][col])
               
            }
        }
    }
    
    
    func calculateIsMineLocationForSquare(_ square: Square) {
        square.isMineLocation = ((arc4random()%10) == 0) // 1-in-20 chance that each location contains a mine
        print("mineLocations ==== ",square.isMineLocation)
    }
    
    func calculateNumNeighborMinesForSquare(_ square : Square) {
      
     
        var numNeighboringMines = 0
        numNeighboringMines += 1
        square.numNeighboringMines = numNeighboringMines
       
    }
    
    
}
