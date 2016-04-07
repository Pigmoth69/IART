minimax(E,_,V,_):- estado_final(E,V).

minimax(E, max, V, J):-
	findall(E1, sucessor(E,max,E1), LJ),
	max_value(LJ, V, J).

minimax(E, min, V, J):-
	findall(E1, sucessor(E,min,E1), LJ),
	min_value(LJ, V, J).

max_value([E], V, E):-
	minimax(E, min, V, _).

max_value([E | Es], MV, ME):-
	minimax(E, min, V, _),
	max_value(Es, V2, M2),
	(V > V2, !, MV = V, ME = E; MV = V2, ME = M2).

min_value([E], V, J):-
	minimax(E, max, V, _).

min_value([E | Es], MV, ME):-
	minimax(E, max, V, _),
	min_value(Es, V2, M2),
	(V =< V2, !, MV = V, ME = E;MV = V2, ME = M2).