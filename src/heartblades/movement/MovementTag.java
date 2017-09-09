package heartblades.movement;

public enum MovementTag {

	LIGHT, WALK, FLY, SWIM, GHOST;

	public static MovementTag[] land = { LIGHT, WALK, FLY, GHOST };
	public static MovementTag[] sea = { LIGHT, FLY, SWIM, GHOST };
	public static MovementTag[] stone = { GHOST };
	public static MovementTag[] gap = { FLY, LIGHT, GHOST };
	public static MovementTag[] obstacle = { LIGHT, GHOST };

}
