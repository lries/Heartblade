package heartblades.movement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import heartblades.map.Dungeon;

public class Pathfinder {
	public static int maximumSearchDepth = 50000;

	public static List<int[]> findPathAStar( Dungeon d, int startX, int startY, int goalX, int goalY, MovementTag[] movementTypes,
			boolean checkActors, boolean ignoreGoalActors ) {
		List<Node> frontier = new ArrayList<Node>( );
		Node start = new Node( startX, startY );
		Node end = new Node( goalX, goalY );
		start.value = start.heuristic( end );
		start.cost = 0;
		frontier.add( start );
		List<Node> visited = new ArrayList<Node>( );
		int nodesVisitedCount = 0; 
		Node currentNode;

		while ( !frontier.isEmpty( ) ) {
			currentNode = frontier.remove( 0 );
			nodesVisitedCount++;
			visited.add( currentNode );
			if ( currentNode.equals( end ) ) {
				break;
			}
			boolean check = checkActors && (currentNode.x != goalX || currentNode.y != goalY || !ignoreGoalActors);
			List<Node> newNodes = currentNode.neighbors( d, movementTypes, check, end );
			for ( Node newNode : newNodes ) {
				newNode.parent = currentNode;
				if ( newNode.x == currentNode.x | newNode.y == currentNode.y )
					newNode.cost = currentNode.cost + 1;
				else
					newNode.cost = currentNode.cost + Math.sqrt( 2 );
				newNode.value = newNode.heuristic( end );
				boolean addToFrontier = true;
				for ( Node previouslyVisted : visited ) {
					if ( previouslyVisted.x == newNode.x && previouslyVisted.y == newNode.y ) {
						addToFrontier = false;
					}
				}
				if ( addToFrontier && !frontier.contains( newNode ) ) {
					boolean added = false;
					for ( int i = 0; i < frontier.size( ); i++ ) {
						if ( newNode.cost + newNode.value < frontier.get( i ).cost + frontier.get( i ).value ) {
							frontier.add( i, newNode );
							added = true;
							break;
						}
					}
					if ( !added ) {
						frontier.add( newNode );
					}
				}
			}
			if ( nodesVisitedCount > maximumSearchDepth ) {
				System.out.println( "Node limit reached" );
				return null;
			}
		}
		System.out.println( start.toString( ) + " " + end.toString( ) );
		return Backwards( visited.get( visited.size( ) - 1 ), start );

	}

	/***************************************************
	 * Trace back through visited nodes to find a path.
	 * 
	 * @param visits
	 *            the visit list from e.g. A*
	 * @param start
	 *            the start of the path.
	 * @return a List<int[]> path
	 ***************************************************/
	public static List<int[]> Backwards( Node end, Node start ) {
		// returns path from Start to Goal as a List. simply traces back through
		// the visited nodes and finds the ideal path.
		List<int[]> bw = new ArrayList<int[]>( );
		Node c = end;
		while ( c != null && c.parent != null && (c.x != start.x || c.y != start.y) ) {
			int[] point = { c.x, c.y };
			bw.add( point );
			c = c.parent;
		}
		Collections.reverse( bw );
		for ( int[] element : bw ) {
			System.out.println( element.length + " " + element[0] + " " + element[1] );
		}
		return bw;
	}

	/*********************************************
	 * Node: a private class used for pathfinding
	 *********************************************/
	private static class Node {
		public int x;
		public int y;
		public double cost;
		public double value;
		public Node parent;

		public Node( int x, int y ) {
			this.x = x;
			this.y = y;
			this.cost = 0;
			this.value = 0; // heuristic value of a node - kept because
							// recalculating it all the time is expensive! :V
			this.parent = null;
		}

		public List<Node> neighbors( Dungeon floorMap, MovementTag[] movementTypes, boolean checkActors, Node goal ) {
			List<Node> ret = new ArrayList<Node>( );
			for ( int ix = Math.max( 0, x - 1 ); ix <= Math.min( floorMap.getTiles( ).length - 1, x + 1 ); ix++ ) {
				for ( int iy = Math.max( 0, y - 1 ); iy <= Math.min( floorMap.getTiles( )[x].length - 1,
						y + 1 ); iy++ ) {
					if ( ix == x && iy == y )
						continue;
					if ( floorMap.getActorAt( ix, iy ) != null && checkActors && !this.equals( goal ) ) {
						continue;
					}
					if ( floorMap.getTileAt( ix, iy ).canWalk( movementTypes ) )
						ret.add( 0, new Node( ix, iy ) );
				}
			}
			return ret;
		}

		public int heuristic( Node goal ) {
			return ( int ) (Math.sqrt( (goal.x - x) * (goal.x - x) + (goal.y - y) * (goal.y - y) ));
		}

		@Override
		public String toString( ) {
			// method for node-to-string conversion
			return "(" + x + "," + y + ")";
		}

		@Override
		public boolean equals( Object o ) {
			// returns true if the x and y values are equal
			if ( !(o instanceof Node) ) {
				return false;
			}
			Node n = ( Node ) o;
			return (this.x == n.x && this.y == n.y);
		}

	}

}
