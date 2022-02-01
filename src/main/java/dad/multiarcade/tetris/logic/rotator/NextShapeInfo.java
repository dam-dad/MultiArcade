package dad.multiarcade.tetris.logic.rotator;

import dad.multiarcade.tetris.logic.MatrixOperations;

public final class NextShapeInfo {

	private final int[][] shape;
	private final int position;

	public NextShapeInfo(final int[][] shape, final int position) {
		this.shape = shape;
		this.position = position;
	}

	public int[][] getShape() {
		return MatrixOperations.copy(shape);
	}

	public int getPosition() {
		return position;
	}
}
