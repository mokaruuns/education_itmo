// final solution
"use strict";
const VARIABLES = {
    "x": 0,
    "y": 1,
    "z": 2,
};
const commonOperation = operation => (...operands) => (...values) => {
    const result = [];
    operands.map(operand =>
        result.push(operand(values))
    );
    return operation(...result);
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
const cube = commonOperation(a => a * a * a);
const cuberoot = commonOperation(a => Math.cbrt(a));