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
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверка на корректность позиций
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column)
                || !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }

        // Конь не может стоять на месте
        if (line == toLine && column == toColumn) {
            return false;
        }

        // Проверка всех 8 возможных ходов коня
        if ((toLine == line + 2 && toColumn == column + 1) ||
                (toLine == line + 2 && toColumn == column - 1) ||
                (toLine == line - 2 && toColumn == column + 1) ||
                (toLine == line - 2 && toColumn == column - 1) ||
                (toLine == line + 1 && toColumn == column + 2) ||
                (toLine == line + 1 && toColumn == column - 2) ||
                (toLine == line - 1 && toColumn == column + 2) ||
                (toLine == line - 1 && toColumn == column - 2)) {

            // Проверка, чтобы конь не ел свою фигуру
            ChessPiece targetPiece = chessBoard.getPiece(toLine, toColumn);
            if (targetPiece == null) {
                return true; // Конечная позиция свободна
            } else {
                return !targetPiece.getColor().equals(this.color); // Конечная позиция занята вражеской фигурой
            }
        }

        return false;
    }

    @Override
    public String getSymbol() {
        return "H";
    }
}