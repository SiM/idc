package idc;

final class IdGen {
	static private int id = 0;

	public IdGen() {
		// nothing to do
	}

	static public int getID() {
		integrity();
		id = id + 1;
		integrity();
		return id;
	}

	static public void integrity() {
		assert (id >= 0);
	}
}