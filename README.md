# Mgm

## description:
Simple terminal game.
To win the party,
you need to block all possible cells,
where the opponent can put his figure

## usage:

```sh
# mgm <BoardSize>
# where BoardSize is number beetween 3 and 9 (including 3 and 9)
mgm 5
```

## commands:

```yaml
: <letter><number> # go to column <letter>, to position <number>
: a1               # example
: <number><letter> # same as first
: 1a               # example
: exit             # end game and make draw
```
## compile + install:

as first you need <a href="https://docs.scala-lang.org/getting-started.html">sbt</a>
then
```sh
# linux / mac
git clone https://github.com/Eldyj/mgm mgm
cd mgm
sbt compile
cp target/scala-*/mgm-out ~/.local/bin/mgm
```

## what mean all thoose characters?

```yaml
"#": blue player (put first)
"%": red player
"+": you can put there
```
