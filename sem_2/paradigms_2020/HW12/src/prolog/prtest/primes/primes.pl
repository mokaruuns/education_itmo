init(MAX_N):-
    fill_table(MAX_N).

forj(J, _, N):-
    J > N.

forj(J, I, N):-
    J =< N,
    assert(unprime_table(J, I)),
    J1 is J + I,
    forj(J1, I, N). % тут огромная бага( с !

fori(I, N):-
    I2 is I * I,
    I2 > N.

fori(I, N):-
    I2 is I * I,
    I2 =< N,
    ((not(unprime_table(I, R)),
    forj(I2, I, N)) ;
    unprime_table(I, R)),
    I1 is I + 1,
    fori(I1, N).

forn(I, N):-
    I > N.

forn(I, N):-
    I =< N,
    I1 is I + 2,
    forn(I1, N).

fill_table(N):-
    assert(unprime_table(1, 1)),
    fori(2, N).

prime(N):-
    not(unprime_table(N, _)).

composite(N):-
    unprime_table(N, _).

get_new_m(1, R):-!.
get_new_m(N, R):-
    not(unprime_table(N, _)),
    append([], [N], R).

get_new_m(N, R):-
    unprime_table(N, D),
    N1 is N / D,
    get_new_m(N1, R1),
    append(R1, [D], R), !.

mul_list(1, []).
mul_list(M, [H | T]):-
    mul_list(M1, T),
    M is H * M1.

prime_divisors(1, []):-!.
prime_divisors(N, R):-
    var(N),
    mul_list(N, R),
    prime_divisors(N, R).

prime_divisors(N, V):-
    nonvar(N),
    get_new_m(N, V1),
    reverse(V1, V).


remove_duplicates([],[]).
remove_duplicates([H | T], List) :-
     member(H, T),
     remove_duplicates(T, List).
remove_duplicates([H | T], [H | T1]) :-
     not (member(H, T)),
     remove_duplicates(T, T1).
unique_prime_divisors(1, []):-!.
unique_prime_divisors(N, R):-
    var(R),
    prime_divisors(N, R1),
    remove_duplicates(R1, R), !.