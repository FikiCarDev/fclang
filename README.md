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
print "Pocetak"

print "Tipovi podataka"

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

print "Izrazi"

decimal r = 3.5
decimal alfa = 60.0
decimal obim_kruga = 2.0 * r * pi
decimal povrsina_kruga = r * r * pi
decimal duzina_luka = ( r * pi * alfa ) / 180.0

print obim_kruga
print povrsina_kruga
print duzina_luka
print ""


print "If else"

int aleksine_jabuke = 6
int jovanove_jabuke = 9

if ( aleksine_jabuke > jovanove_jabuke ) {
    print "Aleksa ima vise jabuka od Jovana"
} else {
    print "Jovan ima vise jabuka od Alekse"
}

bool suncano = true
bool oblacno = false
bool toplo = true
bool hladno = false

if ( ! oblacno ) {
    print "Suncano je."
    if ( toplo ) {
        print "Toplo je."
    } else {
        print "Hladno je."
    }
} else {
    print "Oblacno je."
    if ( toplo ) {
        print "Toplo je."
    } else {
        print "Hladno je."
    }
}
print ""


print "Petlje"

for ( i | 0 | 5 | < | + | 1 ) {
    print i
}

int faktorijal = 1
int broj = 5

for ( j | 1 | broj + 1 | < | + | 1 ) {
    int faktorijal = faktorijal * j
}

print faktorijal

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
print "Izasao sam iz petlji."
print ""

print "Dobio sam:"

goto L-spasi_me
print 1

L-spasi_me
print 5

print "Kraj"
```

### Author

[@FikiCarDev](https://github.com/FikiCarDev)
