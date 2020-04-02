"use strict";


// const commonOperation =
//     op => (...operands) => (...values) => op(...operands.map(curOperand => curOperand(...values)));

const commonOperation = operation => (...operands) => (x, y, z) => {
    let result = [];
    operands.map(operand =>
        result.push(operand(x, y, z))
    );
    return operation(...result);
};

const cnst = value => () => value;

//const variable = name => value => value;

const variable = name => (...values) => {return values[VARIABLES[name]]};

const VARIABLES = {
    "x" : 0,
    "y" : 1,
    "z" : 2,
};
const add = commonOperation((a, b) => a + b);

const subtract = commonOperation((a, b) => a - b);

const multiply = commonOperation((a, b) => a * b);

const divide = commonOperation((a, b) => a / b);

const negate = commonOperation(a => -a);

// const sin = commonOperation(a => Math.sin(a));
//
// const cos = commonOperation(a => Math.cos(a));

const cube = commonOperation(a => a * a * a);

const cuberoot = commonOperation(a => Math.cbrt(a));

// const pi = cnst(Math.PI);
//
// const e = cnst(Math.E);

// let expt = add(cnst(5), divide(variable("x"), cnst(2)));

// let expr =
//     add(
//         subtract(
//             multiply(
//                 variable("x"),
//                 variable("x")),
//             multiply(
//                 cnst(2),
//                 variable("x"))),
//         cnst(1));
// for (let i = 0; i < 10; i++){
//     println(expr(i));
// }
const expr = cuberoot(add(variable('x'), variable('y')));
console.log(expr(0.00000000000000000000,0.00000000000000000000,0.00000000000000000000));