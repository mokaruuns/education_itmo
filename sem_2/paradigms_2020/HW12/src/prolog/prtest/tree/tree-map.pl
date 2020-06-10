getIndex([], []).
getIndex([H|T], R):-
    H = '=',
    R = [T], !.
getIndex([H|T], R):-
    not(H = '='),
    getIndex(T, W),
    append(W, [H], R), !.

getKV((K, V), K, V).
getKV(S, K, V):-
    atom_chars(S, L),
    getIndex(L, [H | T]),
    atom_chars(H1, H),
    V = H1,
    reverse(T, T1),
    number_chars(T2, T1),
   	K = T2,
    !.

build([], []).
build([H | T], MT) :-
    getKV(H, K, V),
    append([K], ['=', V], Z),
    atom_chars(Z1, Z),
    build(T, W),
    append(W, [Z1], MT), !.

map_build(L, T):-
	build(L, T1),
	reverse(T1, T), !.
map_get(L, K, V) :-
    get_slice(L, K, V), !.

get_slice(L, K, V) :-
    divide(L, LL, [RH|RT]),
    getKV(RH, K1, V1),
    K = K1,
    V = V1, !.
get_slice(L, K, V) :-
    divide(L, LL, [RH|RT]),
    getKV(RH, K1, V1),
    K > K1,
    get_slice(RT, K, V), !.
get_slice(L, K, V) :-
    divide(L, LL, [RH|RT]),
    getKV(RH, K1, V1),
    K < K1,
    get_slice(LL, K, V), !.

divide(L, LL, RL):-
    divide(L, L, LL, RL).
divide([_,_|T], [A|L], [A|LL], RL):-
    divide(T, L, LL, RL), !.
divide(_, LR, [], LR).

%split_replace(Map, Key, Value, Result):-
%    divide(Map, LL, [RH|RT]),
%    getKV(RH, K1, V1),
%    Key = K1,
%    number_chars(Key, R2),
%    number_chars(Value, [HR| TR]),
%    append(R2, ['=', HR], R4),
%    atom_chars(RRR, R4),
%    append(LL,[RRR], R1),
%    append(R1, RT, Result),
%    print('='), !.
%
%split_replace(Map, Key, Value, Result):-
%    divide(Map, LL, [RH|RT]),
%    getKV(RH, K1, V1),
%    Key < K1,
%    print('<'),
%    split_replace(LL, Key, Value, R),
%    print('<'),
%    append(R, RT, Result), !.
%
%split_replace(Map, Key, Value, Result):-
%    divide(Map, LL, [RH|RT]),
%    getKV(RH, K1, V1),
%    Key > K1,
%    print('>'),
%    split_replace(RT, Key, Value, R),
%    append(LL, [RH], R1),
%    append(R1, RT, Result),
%    print('>'),!.


replace([], _, _, _).
replace([H | T], Key, Value, Result):-
    getKV(H, K1, V1),
    not (Key = K1),
    replace(T, Key, Value, R),
    append(R, [H], Result),!.

replace([H | T], Key, Value, Result):-
    getKV(H, K1, V1),
    Key = K1,
    number_chars(Key, R2),
    atom_chars(Value, [RH | RT]),
    append(R2, ['=', RH], R4),
    append(R4, RT, R),
    atom_chars(R1, R),
    append([R1], T, RR),
    reverse(RR , Result).
    !.

map_replace(Map, Key, Value, Result):-
	replace(Map, Key, Value, RR),
	reverse(RR, Result), !.
    