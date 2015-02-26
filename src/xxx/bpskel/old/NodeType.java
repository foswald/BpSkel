package xxx.bpskel.old;


public enum NodeType{
	TASK,
	XOR_SPLIT, XOR_JOIN,
	AND_SPLIT, AND_JOIN,
	OR_SPLIT, OR_JOIN,
	START, END,
	UNDEFINED // for arbitrary placeholder (e.g. any number of succeeding objects)
}
