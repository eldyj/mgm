# Mgm

## description:
Simple terminal game. <br/>
To win the party, <br/>
you need to block all possible cells, <br/>
where the opponent can put his figure <br/>

## usage:

```sh
mgm -help
```

## in-game commands:

```yaml
: <letter><number> # go to column <letter>, to position <number>
: a1               # example
: <number><letter> # same as first
: 1a               # example
: exit             # end game and make draw
```
## compile + install:

as first you need <a href="https://docs.scala-lang.org/getting-started.html">sbt</a> <br/>
then
```sh
# linux / mac
git clone https://github.com/Eldyj/mgm mgm
cd mgm
sbt compile --native
cp target/scala-*/mgm-out ~/.local/bin/mgm
```

## what mean all thoose characters?

```yaml
"#": blue player (put first)
"%": red player
"+": you can put there
```
