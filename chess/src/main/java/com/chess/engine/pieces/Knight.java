package main.java.com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;

import main.java.com.chess.engine.Alliance;
import main.java.com.chess.engine.board.Move;
import main.java.com.chess.engine.board.Board;
import main.java.com.chess.engine.board.Tile;

public class Knight extends Piece {

    private static final int[] CANDIDATE_MOVE_COORDINATES = {-17, -15, -10, -6, 6, 10, 15, 17};

    public Knight(final int piecePos, final Alliance pieceAlliance) {
        super(piecePos, pieceAlliance);
    }

    @Override
    public List<Move> calculateLegalMoves(Board board) {

        int candidateDestinationCoord;
        final List<Move> legalMoves = new ArrayList<>();

        for (final int currentCandidate : CANDIDATE_MOVE_COORDINATES) {
            candidateDestinationCoord = this.piecePos + currentCandidate;

            if(true){
                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoord);

                if(!candidateDestinationTile.isTileOccupied()){
                    legalMoves.add(new Move());
                } else {
                    final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                    final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();

                    if(this.pieceAlliance != pieceAlliance){
                        legalMoves.add(new Move());
                    }
                }
            }
        }


        return ImmutableList.copyOf(legalMoves);
    }
}
