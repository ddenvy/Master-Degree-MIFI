package chess.pieces;

import chess.board.ChessBoard;

public class Queen extends ChessPiece {

    public Queen(String color) {
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

        // Ферзь не может оставаться на месте
        if (line == toLine && column == toColumn) {
            return false;
        }

        // Проверка на движение по вертикали, горизонтали или диагонали
        if (line == toLine || column == toColumn ||
                (line < toLine && column < toColumn && toLine - line == toColumn - column) ||
                (line < toLine && column > toColumn && toLine - line == column - toColumn) ||
                (line > toLine && column < toColumn && line - toLine == toColumn - column) ||
                (line > toLine && column > toColumn && line - toLine == column - toColumn)) {

            // Определение направления движения
            int lineStep = (line == toLine) ? 0 : (toLine > line) ? 1 : -1;
            int columnStep = (column == toColumn) ? 0 : (toColumn > column) ? 1 : -1;

            // Проверка на отсутствие фигур на пути
            int currentLine = line + lineStep;
            int currentColumn = column + columnStep;
            while (currentLine != toLine || currentColumn != toColumn) {
                if (chessBoard.getPiece(currentLine, currentColumn) != null) {
                    return false; // Есть фигура на пути
                }
                currentLine += lineStep;
                currentColumn += columnStep;
            }

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
        return "Q";
    }
}
