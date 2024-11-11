package chess.pieces;

import chess.board.ChessBoard;

public class Rook extends ChessPiece {

    public Rook(String color){
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        return false;
    }

    @Override
    public String getSymbol() {
        return "R";
    }
}
