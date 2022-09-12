# FCLang
FikiCar Language or fclang for short is a new beginner-friendly
programming language created and optimized for usage
on mobile phones.

Mobile online learning platform [NDP](https://github.com/FikiCarDev/ndp) 
is using it.

### About interpreter
Fclang interpreter is written in Java to take advantage of portability
of JVM to mobile devices, as it is targeted to them.

Lexer analyses code and creates tokens per rules defined in 
[token list](https://github.com/FikiCarDev/fclang/blob/master/info/tokensList.txt).

Parser is of recursive descending type, and the grammar can be found in
documentation and comments in the appropriate classes.

### Example
The following code is displaying some of the features that fclang supports.
More code snippets are found in [scripts folder](https://github.com/FikiCarDev/fclang/tree/master/scripts).

```py
print "Data types"

string str_1 = "Hello world!"
int int_1 = 16
decimal pi = 3.1415926535
bool ok = true

print ""
print str_1
print int_1 = 16
print pi
print true
print ""

print "Expressions"

decimal r = 3.5
decimal alfa = 60.0
decimal circle_circumference = 2.0 * r * pi
decimal circle_area = r * r * pi
decimal arc_length = ( r * pi * alfa ) / 180.0

print circle_circumference
print circle_area
print arc_length
print ""


print "If else"

bool sunny = true
bool cloudy = false
bool warm = true
bool cold = false

if ( !cloudy ) {
    print "It is sunny"
    if ( warm ) {
        print "It is warm"
    } else {
        print "It is cold"
    }
} else {
    print "It is cloudy"
    if ( warm ) {
        print "It is warm"
    } else {
        print "It is cold"
    }
}
print ""


print "For loops"

int sum = 0

for ( i | 0 | 5 | < | + | 1 ) {
    int sum = sum * i
    for ( j | 0 | 5 | < | + | 1 ) {
        int sum = sum + j
        if ( sum >= 50 ) {
            print sum
            goto L-exit_1
        }
    }
}

L-exit_1

print "Exit out of loop"
```

### Author

[@FikiCarDev](https://github.com/FikiCarDev)
