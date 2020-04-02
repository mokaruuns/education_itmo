// for delay
"use strict";

function Operation() {
    this.values = arguments;
}

Operation.prototype.evaluate = function () {
    let res = [];
    for (let value of this.values) {
        res.push(value.evaluate.apply(value, arguments));
    }
    return this.calc.apply(null, res);
};
Operation.prototype.toString = function () {
    let res = "";
    for (let value of this.values) {
        res += value + " ";
    }
    return res + this.operationLiteral;
};
const variables = ["x", "y", "z"];

function Const(value) {
    this.value = value;
}

Const.prototype.evaluate = function () {
    return this.value;
};
Const.prototype.toString = function () {
    return this.value.toString();
};

function Variable(name) {
    this.index = variables.indexOf(name);
}

Variable.prototype.evaluate = function () {
    return arguments[this.index];
};
Variable.prototype.toString = function () {
    return variables[this.index];
};

function Add(left, right) {
    Operation.call(this, left, right);
    this.operationLiteral = "+";
}

Add.prototype = Object.create(Operation.prototype);
Add.prototype.calc = (left, right) => left + right;

function Subtract(left, right) {
    Operation.call(this, left, right);
    this.operationLiteral = "-";
}

Subtract.prototype = Object.create(Operation.prototype);
Subtract.prototype.calc = (left, right) => left - right;

function Multiply(left, right) {
    Operation.call(this, left, right);
    this.operationLiteral = "*";
}

Multiply.prototype = Object.create(Operation.prototype);
Multiply.prototype.calc = (left, right) => left * right;

function Divide(left, right) {
    Operation.call(this, left, right);
    this.operationLiteral = "/";
}

Divide.prototype = Object.create(Operation.prototype);
Divide.prototype.calc = (left, right) => left / right;

function Negate(value) {
    Operation.call(this, value);
    this.operationLiteral = "negate";
}

Negate.prototype = Object.create(Operation.prototype);
Negate.prototype.calc = (value) => -value;

function Max5(...values) {
    Operation.call(this, ...values);
    this.operationLiteral = "max5";
}

Max5.prototype = Object.create(Operation.prototype);
Max5.prototype.calc = (...values) => Math.max(...values);

function Min3(...values) {
    Operation.call(this, ...values);
    this.operationLiteral = "min3";
}

Min3.prototype = Object.create(Operation.prototype);
Min3.prototype.calc = (...values) => Math.min(...values);