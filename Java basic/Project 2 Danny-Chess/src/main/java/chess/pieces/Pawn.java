package chess.pieces;

import chess.board.ChessBoard;

public class Pawn extends ChessPiece {

    public Pawn(String color){
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
        return "P";
    }
}
