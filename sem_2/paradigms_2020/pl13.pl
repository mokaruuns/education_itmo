range(L, L, []).
range(N, L, [N | T]) :- N < L, N1 is N + 1, range(N1, L, T).

range_null(L, L, []).
range_null(N, L, [0 | T]) :- N < L, N1 is N + 1, assert(fib_table(N1, 0)), range_null(N1, L, T).


iter(L, L, K, []).
	
iter(N, L, K, [N | T]) :- 
	N < L, 
	fib_table(N, 0),
	assert(fib_table(N, N)),
	assert(pr_table(K, N)),
	K is K + 1.
	
iter(N, L, K, [N | T]) :- 
	N < L, 
	N1 is N + 1,
	iter(N1, L, T).


prime(N):-
	range_null(0, 10, LP),
	iter(1, 10, 1, R).
	