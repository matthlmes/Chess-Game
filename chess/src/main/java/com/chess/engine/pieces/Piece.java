package com.chess.engine.pieces;

import main.java.com.chess.engine.Alliance;
import main.java.com.chess.engine.board.Move;
import main.java.com.chess.engine.board.Board;

import java.util.List;

public abstract class Piece {

    protected final int piecePos;
    protected final Alliance pieceAlliance;

    Piece(final int piecePos, final Alliance pieceAlliance){
        this.piecePos = piecePos;
        this.pieceAlliance = pieceAlliance;
    }

    public abstract List<Move> calculateLegalMoves(final Board board);

}
