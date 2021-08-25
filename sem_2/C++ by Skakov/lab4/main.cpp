    #include <iostream>
    #include <vector>
    #include <algorithm>
    #include <cmath>
    #include <fstream>

    using namespace std;

    class Number {
    private:
        bool is_nan = false;
        bool is_negate = false;
        short sep = 9;
        vector<unsigned int> digits;

    public:
        Number() = default;

        explicit Number(string unparsed_number) {
            is_negate = (unparsed_number[0] == '-');
            size_t pos = unparsed_number.size();
            for (size_t i = (unparsed_number.size() - is_negate) / sep; i > 0; i--) {
                pos -= sep;
                string s = unparsed_number.substr(pos, sep);
                digits.push_back(stoi(s));
            }
            if (pos - is_negate != 0) {
                string s = unparsed_number.substr(is_negate, (unparsed_number.size() - is_negate) % sep);
                digits.push_back(stoi(s));
            }
        }

        static Number negate(const Number& A) {
            Number B = A;
            B.is_negate = !A.is_negate;
            return B;
        }

        static Number sum(const Number& A, const Number& B) {
            if (A.is_negate) {
                if (B.is_negate) return negate(sum(negate(A), negate(B)));
                else return subtract(B, negate(A));
            } else if (B.is_negate) return subtract(A, negate(B));
            Number C;
            size_t max_size = max(A.digits.size(), B.digits.size());
            unsigned long long tmp = 0;
            for (size_t i = 0; i < max_size; i++) {
                long long a = (i < A.digits.size() ? A.digits[i] : 0);
                long long b = (i < B.digits.size() ? B.digits[i] : 0);
                tmp = (a + b + tmp);
                unsigned int c = tmp % (int) 1e9;
                tmp /= 1e9;
                C.digits.push_back(c);
            }
            if (tmp != 0) {
                C.digits.push_back(tmp);
            }
            while (C.digits.size() > 1) {
                if (C.digits.back() == 0) C.digits.pop_back();
                else break;
            }
            return C;
        }


        static Number subtract(const Number& A, const Number& B) {
            if (B.is_negate) return sum(A, negate(B));
            else if (A.is_negate) return negate(sum(negate(A), B));
            else if (compare(A, B) == -1) return negate(subtract(B, A));
            size_t max_size = max(A.digits.size(), B.digits.size());
            long long tmp = 0;
            Number C;
            for (size_t i = 0; i < max_size; i++) {
                long long a = (i < A.digits.size() ? A.digits[i] : 0);
                long long b = (i < B.digits.size() ? B.digits[i] : 0);
                a -= tmp;
                tmp = 0;
                if (a < b) {
                    tmp = 1;
                    a += 1e9;
                }
                C.digits.push_back(a - b);
            }
            while (C.digits.size() > 1) {
                if (C.digits.back() == 0) C.digits.pop_back();
                else break;
            }
            return C;
        }

        static Number multiplication(const Number& A, const Number& B) {
            Number C;
            for (size_t i = 0; i < A.digits.size() + B.digits.size(); i++) {
                C.digits.push_back(0);
            }
            unsigned long long tmp;
            for (size_t i = 0; i < A.digits.size(); i++) {
                tmp = 0;
                for (size_t j = 0; j < B.digits.size() || tmp; j++) {
                    unsigned long long res =
                            A.digits[i] * (unsigned long long) (j < B.digits.size() ? B.digits[j] : 0) + tmp;
                    unsigned long long dig = C.digits[i + j] + res;
                    C.digits[i + j] = dig % (int) 1e9;
                    tmp = dig / (int) 1e9;
                }
            }
            while (C.digits.size() > 1) {
                if (C.digits.back() == 0) C.digits.pop_back();
                else break;
            }
            C.is_negate = (A.is_negate ^ B.is_negate) && (C.digits.back()!=0);
            return C;
        }

        // long realization
        static Number division_to_long(Number A, Number B){
            unsigned int num = B.digits.back();
            if (num == 0) {
                Number N;
                N.is_nan = true;
                return N;
            }
            Number L, T, ONE;
            ONE = Number("1");
            L = Number("0");
            T = Number("2");
            Number R = A;
            R.is_negate = false;
            Number M = division_to_short(sum(L, R), T);
            bool sign_tnp = (A.is_negate ^ B.is_negate) ;
            A.is_negate = false;
            B.is_negate = false;
            while (compare(sum(L, ONE), R) == -1) {
                short tmp = compare(multiplication(M, B), A);
                if (tmp < 0) {
                    L = M;
                } else if (tmp > 0) {
                    R = M;
                } else {
                    return M;
                }
                M = division_to_short(sum(L, R), T);
            }
            M.is_negate = sign_tnp && (M.digits.back()!=0);
            return M;
        }

        // short realization
        static Number division_to_short(const Number& A, const Number& B) {
            unsigned int num = B.digits.back();
            if (num == 0) {
                Number N;
                N.is_nan = true;
                return N;
            }
            unsigned long long tmp = 0;
            Number C;
            for (size_t i = A.digits.size() - 1; i < A.digits.size(); i--) {
                unsigned long long a = (A.digits[i] + tmp * (int) 1e9) / num;
                tmp = (A.digits[i] + tmp * (int) 1e9) % num;
                C.digits.push_back(a);
            }
            reverse(C.digits.begin(), C.digits.end());
            while (C.digits.size() > 1) {
                if (C.digits.back() == 0) C.digits.pop_back();
                else break;
            }
            C.is_negate = (A.is_negate ^ B.is_negate) && (C.digits.back()!=0);
            return C;
        }

        static Number module(const Number& A, Number B) {
            unsigned int num = B.digits.back();
            if (num == 0) {
                Number N;
                N.is_nan = true;
                return N;
            }
            return subtract(A, multiplication(B, division_to_long(A, B)));
        }

        static Number search_sqrt(const Number &A) {
            if (A.is_negate) {
                Number N;
                N.is_nan = true;
                return N;
            }
            Number L, T;
            L = Number("1");
            T = Number("2");
            Number R;
            for (size_t i = 0; i < ceil(A.digits.size() / 2.0); i++) {
                R.digits.push_back(1e9 - 1);
            }
            if (compare(A, R) == -1) {
                R = A;
            }
            Number M1 = Number("0");
            Number M = division_to_short(sum(L, R), T);
            while (compare(M, M1) != 0) {
                short tmp = compare(multiplication(M, M), A);
                if (tmp < 0) {
                    L = M;
                } else if (tmp > 0) {
                    R = M;
                } else {
                    return M;
                }
                M1 = M;
                M = division_to_short(sum(L, R), T);
            }
            return M;
        }

        static short compare(Number A, Number B) {
            if (A.is_negate ^ B.is_negate) {
                if (A.is_negate) {
                    return -1;
                } else {
                    return 1;
                }
            } else {
                if (A.digits.size() == B.digits.size()) {
                    for (size_t i = A.digits.size() - 1; i < A.digits.size(); i--) {
                        if (A.digits[i] < B.digits[i]) {
                            return (!A.is_negate) ? -1 : 1;
                        }
                        if (A.digits[i] > B.digits[i]) {
                            return (!A.is_negate) ? 1 : -1;
                        }
                    }
                    return 0;
                } else {
                    if (A.digits.size() < B.digits.size()) {
                        return (!A.is_negate) ? -1 : 1;
                    } else { return (!A.is_negate) ? 1 : -1; }
                }
            }
        }

        string to_str() {
            string str_result;
            if (is_nan) {
                return "NaN";
            }
            if (is_negate) {
                str_result += "-";
            }
            str_result += to_string(digits.back());
            for (size_t i = digits.size() - 1; i > 0; i--) {
                string str_tmp = "000000000";
                str_tmp += to_string(digits[i - 1]);
                str_result += str_tmp.substr(str_tmp.size() - sep);
            }
            return str_result;
        }
    };

    bool operator==(Number &A, Number &B) {
        return Number::compare(A, B) == 0;
    }

    bool operator<(Number &A, Number &B) {
        return Number::compare(A, B) == -1;
    }

    bool operator>(Number &A, Number &B) {
        return Number::compare(A, B) == 1;
    }

    bool operator<=(Number &A, Number &B) {
        return !(A > B);
    }

    bool operator>=(Number &A, Number &B) {
        return !(A < B);
    }

    bool operator!=(Number &A, Number &B) {
        return !(A == B);
    }

    Number operator+(Number &A, Number &B) {
        return Number::sum(A, B);
    }

    Number operator-(Number &A, Number &B) {
        return Number::subtract(A, B);
    }

    Number operator*(Number &A, Number &B) {
        return Number::multiplication(A, B);
    }

    Number operator/(Number &A, Number &B) {
        return Number::division_to_long(A, B);
    }

    Number operator%(Number &A, Number &B) {
        return Number::module(A, B);
    }

    Number sqrt(Number &A) {
        return Number::search_sqrt(A);
    }

    int main(int argc, char **argv) {
        Number A, B;
        string result;
        string s1, op, s2;
        string fin, fout;
        fin = argv[1];
        fout = argv[2];
        ifstream in(fin);
        if (!in.is_open()) {
            cout << fin << " open error";
            return 1;
        }
        if (!(in >> s1 >> op)) {
            cout << "wrong request, expected first number and operation";
            in.close();
            return 1;
        }
        A = Number(s1);
        if (op == "#") {
            result = sqrt(A).to_str();
        } else {
            if (!(in >> s2)) {
                cout << "wrong request, expected second number";
                in.close();
                return 1;
            }
            B = Number(s2);
            if (op == "+") result = (A + B).to_str();
            else if (op == "-") result = (A - B).to_str();
            else if (op == "*") result = (A * B).to_str();
            else if (op == "/") result = (A / B).to_str();
            else if (op == "%") result = (A % B).to_str();
            else if (op == ">") result = A > B ? "1" : "0";
            else if (op == "<") result = A < B ? "1" : "0";
            else if (op == "==")result = A == B ? "1" : "0";
            else if (op == "!=")result = A != B ? "1" : "0";
            else if (op == ">=")result = A >= B ? "1" : "0";
            else if (op == "<=")result = A <= B ? "1" : "0";
        }
        in.close();
        ofstream out(fout);
        if (!out.is_open()) {
            cout << fout << " open error";
            out.close();
            return 1;
        }
        out << result;
        out.close();
        return 0;
    }
