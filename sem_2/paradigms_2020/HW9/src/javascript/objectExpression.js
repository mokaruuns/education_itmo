const VARIABLES = ['x', 'y', 'z'];
const BRACKET = ["(", ")"];

const Variable = function (name) {
    this.name = name;
    this.evaluate = (...values) => values[VARIABLES.indexOf(this.name)];
    this.prefix = this.toString;
};
Variable.prototype.toString = function () {
    return this.name;
};
const Const = function (value) {
    this.value = value;
    this.evaluate = () => this.value;
    this.prefix = this.toString;
};
Const.prototype.toString = function () {
    return this.value.toString()
};
const Operation = function (...operands) {
    this.getOperands = () => operands;
    this.evaluate = (...values) => {
        const result = operands.map((operand) => operand.evaluate(...values));
        return this.procedure(...result);
    };
    this.toString = () => operands.join(" ") + " " + this.symbol;

    this.prefix = () => "(" + this.symbol + " " + this.getOperands().map((operand) => operand.prefix()).join(" ") + ")";
};

const makeOp = function (procedure, symbol) {
    const result = function (...operands) {
        Operation.apply(this, operands);
    };
    result.prototype = new Operation;
    result.prototype.procedure = procedure;
    result.prototype.symbol = symbol;
    return result;
};

const Add = makeOp((a, b) => a + b, "+");
const Subtract = makeOp((a, b) => a - b, "-");
const Divide = makeOp((a, b) => a / b, "/");
const Multiply = makeOp((a, b) => a * b, "*");
const Negate = makeOp((a) => -a, "negate");
const Min3 = makeOp((a, b, c) => Math.min(a, b, c), "min3");
const Max5 = makeOp((a, b, c, d, e) => Math.max(a, b, c, d, e), "max5");
const ArcTan = makeOp(a => Math.atan(a), "atan");
const Exp = makeOp(a => Math.exp(a), "exp");
const OPERATIONS = {
    "+": Add,
    "-": Subtract,
    "/": Divide,
    "*": Multiply,
    "negate": Negate,
    "atan": ArcTan,
    "exp": Exp
};
const COUNT_ARGS = {
    "+": 2,
    "-": 2,
    "/": 2,
    "*": 2,
    "negate": 1,
    "atan": 1,
    "exp": 1
};
const Exception = (message) => {
    const e = function (...args) {
        this.message = message(...args);
    };
    e.prototype = new Error;
    return e;
};

const UnexpectedSymbolException = Exception((expr, index) => "Expected x,y,z constant or variable : " + expr + " on position(" + index + ")");

const ManyOperandsException = Exception((expr) => "Too much operands for operation in " + expr);

const FewOperandException = Exception((expr) => "Not enough operands for operation in" + expr);

const OperationMissedException = Exception((expr) => "Expected '+, -, *, /, atan, exp' in " + expr);

const CloseBracketMissedException = Exception((expr, index) => "Expected ) in position(" + index + ") in " + expr);

function isWhitespace(suspect) {
    return suspect === " ";
}

function Parser(expression, parse) {
    this.token = '';
    this.parse = parse;
    this.expression = expression;
    this.position = 0;
}

Parser.prototype.skipWhitespace = function () {
    while (this.position < this.expression.length && isWhitespace(this.expression[this.position])) this.position++;
};
Parser.prototype.getNextToken = function () {
    this.skipWhitespace();
    let res = '';
    if (BRACKET.includes(this.expression[this.position])) {
        res = this.expression[this.position];
        this.position++;
    } else {
        let i = this.position;
        while (!BRACKET.includes(this.expression[i]) && !isWhitespace(this.expression[i]) && i < this.expression.length) i++;
        res = this.expression.slice(this.position, i);
        this.position = i;
    }
    this.skipWhitespace();
    this.token = res;
};

const parsePrefix = function (expression) {
    const parser = new Parser(expression, function () {
            if (this.token === "(") {
                this.getNextToken();
                if (this.token in OPERATIONS) {
                    let arguments = [];
                    let operation = this.token;
                    this.getNextToken();
                    while (this.token !== ")" && this.position < expression.length) {
                        arguments.push(this.parse());
                        this.getNextToken();
                    }
                    if (this.token !== ")") throw new CloseBracketMissedException(expression, this.position);
                    else if (COUNT_ARGS[operation] > arguments.length) throw new FewOperandException(expression);
                    else if (COUNT_ARGS[operation] < arguments.length) throw new ManyOperandsException(expression);
                    return new OPERATIONS[operation](...arguments);
                } else throw new OperationMissedException(expression, this.position);
            } else if (+this.token) return new Const(+this.token);
            else if (VARIABLES.includes(this.token)) return new Variable(this.token);
            else throw new UnexpectedSymbolException(expression, this.position);
        }
    );
    parser.getNextToken();
    const result = parser.parse();
    if (parser.position < expression.length) throw new UnexpectedSymbolException(expression, parser.position);
    return result;
};