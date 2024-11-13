package chess.pieces;

import chess.board.ChessBoard;

public class Pawn extends ChessPiece {

    public Pawn(String color) {
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

        // Пешки не могут оставаться на месте
        if (line == toLine && column == toColumn) {
            return false;
        }

        // Определение направления движения
        int direction = this.color.equals("White") ? 1 : -1;

        // Движение на одну клетку вперед
        if (column == toColumn && line + direction == toLine) {
            return chessBoard.getPiece(toLine, toColumn) == null; // Ничего не блокирует путь
        }

        // Движение на две клетки вперед при первом ходе
        if ((this.color.equals("White") && line == 1 || this.color.equals("Black") && line == 6) &&
                column == toColumn && line + 2 * direction == toLine) {
            return chessBoard.getPiece(line + direction, column) == null &&
                    chessBoard.getPiece(toLine, toColumn) == null; // Ничего не блокирует путь
        }

        // Проверка на взятие фигуры по диагонали
        if ((column == toColumn + 1 || column == toColumn - 1) && line + direction == toLine) {
            ChessPiece targetPiece = chessBoard.getPiece(toLine, toColumn);
            return targetPiece != null && !targetPiece.getColor().equals(this.color); // Вражеская фигура на диагонали
        }

        return false;
    }

    @Override
    public String getSymbol() {
        return "P";
    }
}