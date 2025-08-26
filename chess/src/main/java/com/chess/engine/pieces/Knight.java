package main.java.com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableList;

import main.java.com.chess.engine.Alliance;
import main.java.com.chess.engine.board.Move.*;
import main.java.com.chess.engine.board.Board;
import main.java.com.chess.engine.board.BoardUtils;
import main.java.com.chess.engine.board.Tile;

public class Knight extends Piece {

    private static final int[] CANDIDATE_MOVE_COORDINATES = {-17, -15, -10, -6, 6, 10, 15, 17};

    public Knight(final int piecePos, final Alliance pieceAlliance) {
        super(piecePos, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATES) {
            final int candidateDestinationCoord = this.piecePos + currentCandidateOffset;

            if(BoardUtils.isValidateTileCoordinate(candidateDestinationCoord)){
                
                if(isFirstColumnExclusion(this.piecePos, currentCandidateOffset) ||
                   isSecondColumnExclusion(this.piecePos, currentCandidateOffset) ||
                   isSeventhColumnExclusion(this.piecePos, currentCandidateOffset) ||
                   isEighthColumnExclusion(this.piecePos, currentCandidateOffset)){
                    continue;
                }
                
                
                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoord);

                if(!candidateDestinationTile.isTileOccupied()){
                    legalMoves.add(new MajorMove(board, this, candidateDestinationCoord));
                } else {
                    final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                    final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();

                    if(this.pieceAlliance != pieceAlliance){
                        legalMoves.add(new AttackMove(board, this, candidateDestinationCoord, pieceAtDestination));
                    }
                }
            }
        }


        return ImmutableList.copyOf(legalMoves);
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -17 || candidateOffset == -10 ||
                candidateOffset == 6 || candidateOffset == 15);
    }

    private static boolean isSecondColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.SECOND_COLUMN[currentPosition] && (candidateOffset == -10 || candidateOffset == 6);
    }

    private static boolean isSeventhColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.SEVENTH_COLUMN[currentPosition] && (candidateOffset == -6 || candidateOffset == 10);
    }

    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOffset == -15 || candidateOffset == -6 ||
                candidateOffset == 10 || candidateOffset == 17);
    }
}
