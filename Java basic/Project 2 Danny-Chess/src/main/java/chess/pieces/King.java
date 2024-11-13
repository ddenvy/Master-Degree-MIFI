package chess.pieces;

import chess.board.ChessBoard;

public class King extends ChessPiece {

    public King(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверка на валидность позиций
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) ||
                !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }

        // Король не может оставаться на месте
        if (line == toLine && column == toColumn) {
            return false;
        }

        // Проверка на движение на одну клетку в любом направлении
        if (Math.abs(line - toLine) <= 1 && Math.abs(column - toColumn) <= 1) {
            // Проверка на цвет фигуры на конечной позиции
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
        return "K";
    }
}