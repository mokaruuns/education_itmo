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
        res += value + " ";``
    }
    return res + this.operation;
};
const variables = ["x", "y", "z"];

function Const(value) {
    this.value = value;
    this.evaluate = () => value;
    this.toString = () => value.toString();
}

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
    this.operation = "+";
    this.calc = (left, right) => left + right;
}
Add.prototype = Object.create(Operation.prototype);
function Subtract(left, right) {
    Operation.call(this, left, right);
    this.operation = "-";
    this.calc = (left, right) => left - right;
}
Subtract.prototype = Object.create(Operation.prototype);
function Multiply(left, right) {
    Operation.call(this, left, right);
    this.operation = "*";
    this.calc = (left, right) => left * right;
}
Multiply.prototype = Object.create(Operation.prototype);
function Divide(left, right) {
    Operation.call(this, left, right);
    this.operation = "/";
    this.calc = (left, right) => left / right;
}
Divide.prototype = Object.create(Operation.prototype);
function Negate(value) {
    Operation.call(this, value);
    this.operation = "negate";
    this.calc = (value) => -value;
}
Negate.prototype = Object.create(Operation.prototype);
function Max5(...values) {
    Operation.call(this, ...values);
    this.operation = "max5";
    this.calc = (...values) => Math.max(...values);
}
Max5.prototype = Object.create(Operation.prototype);
function Min3(...values) {
    Operation.call(this, ...values);
    this.operation = "min3";
    this.calc = (...values) => Math.min(...values);
}
Min3.prototype = Object.create(Operation.prototype);
