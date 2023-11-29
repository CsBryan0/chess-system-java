package boardgame;

public class Board {
	private int rows;
	private int columns;
	private Piece[][] pieces;

	public Board(int rows, int columns) {
		if (rows < 1 || columns < 1) {
			throw new BoardException("Erro ao criar o tabuleiro: É necessário que haja ao menos 1 linha e 1 coluna");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	public Piece piece(int rows, int columns) {
		if (!positionExists(rows, columns)) {
			throw new BoardException("A posição não existe");
		}
		return pieces[rows][columns];
	}

	public Piece piece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("A posição não existe");
		}
		return pieces[position.getRow()][position.getCollum()];
	}

	public void placePiece(Piece piece, Position position) {
		if (thereIsAPiece(position)) {
			throw new BoardException("Já existe uma peça nessa posição! " + position);
		}
		pieces[position.getRow()][position.getCollum()] = piece;
		piece.position = position;
	}

	public Piece removePiece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("A posição não existe");
		}

		if (piece(position) == null) {
			return null;
		}

		Piece aux = piece(position);

		aux.position = null;
		pieces[position.getRow()][position.getCollum()] = null;
		return aux;

	}

	private boolean positionExists(int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}

	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getCollum());
	}

	public boolean thereIsAPiece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("A posição não existe");
		}
		return piece(position) != null;
	}

}
