package chess.pieces;

import chess.board.ChessBoard;

public class Horse extends ChessPiece {

    public Horse(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard,
                                     int line, int column,
                                     int toLine, int toColumn) {

        if (!chessBoard.checkPos(line) && !chessBoard.checkPos(column))
            return false;

        if (!chessBoard.checkPos(toLine) && !chessBoard.checkPos(toColumn))
            return false;

        if (line == column && toLine == toColumn)
            return false;

        //Вычисление изменение координат Коня(алгоритм)
        

    }

    @Override
    public String getSymbol() {
        return "H";
    }
}
