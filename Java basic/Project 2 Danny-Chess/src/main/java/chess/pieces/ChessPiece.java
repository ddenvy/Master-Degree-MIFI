package chess.pieces;

import chess.board.ChessBoard;

public abstract class ChessPiece {
    protected String color;
    protected Boolean check = true;

    protected ChessPiece(String color){
        this.color = color;
    }

    public abstract String getColor();
    public abstract boolean canMoveToPosition(
            ChessBoard chessBoard,
            int line,
            int column,
            int toLine,
            int toColumn
    );
    public abstract String getSymbol();
}
