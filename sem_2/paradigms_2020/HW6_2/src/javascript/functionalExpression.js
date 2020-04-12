"use strict";
const commonOperation = operation => (...operands) => (x, y, z) => {
    let result = [];
    operands.map(operand => result.push(operand(x, y, z)));
    return operation(...result);
};
const VARIABLES = {
    "x": 0,
    "y": 1,
    "z": 2,
};
const cnst = value => () => value;
const variable = name => (...values) => {
    return values[VARIABLES[name]]
};
const add = commonOperation((a, b) => a + b);
const subtract = commonOperation((a, b) => a - b);
const multiply = commonOperation((a, b) => a * b);
const divide = commonOperation((a, b) => a / b);
const negate = commonOperation(a => -a);
const cube = commonOperation((a) => a * a * a);
const cuberoot = commonOperation((a) => Math.cbrt(a));