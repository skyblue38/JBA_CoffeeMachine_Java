# JBA_CoffeeMachine_Java
A project from the Java Core curriculum by JetBrains Academy
## State Machine
| State | Description | Next State |
|:------|:------------|:-----------|
| START | starting up | READY |
| READY | menu choice | BUY, FILL1, TAKE, LEVELS, EXIT |
| BUY   | menu choice | BREW |
| BREW  | deliver coffee | READY |
| FILL1 | Water refill | FILL2 |
| FILL2 | Milk refill  | FILL3 |
| FILL3 | Beans refill | FILL4 |
| FILL4 | Cups refill | READY |
| TAKE | Remove money | READY |
| LEVELS | Show levels | READY |
| EXIT | end processing | SHUTDOWN |
| SHUTDOWN | Turnoff | |

