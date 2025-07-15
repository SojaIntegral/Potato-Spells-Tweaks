
# To make so an effect will replace another, you will need to:

1- Create a list (example: `example.json`) in the `replace_with` folder

2- Create a list with THE SAME NAME, `example.json`, in the `replaced_effects` folder

## How an `example.json` would look like:
Inside `replace_with` folder:
```json
{
  "replace": false,
  "values": [
    "minecraft:speed"
  ]
}
```
Inside `replaced_effects` folder:
```json
{
  "replace": false,
  "values": [
    "minecraft:regeneration",
    "minecraft:jump_boost",
    "minecraft:luck"
  ]
}
```

Any effects in the "replaced" list will be replaced by the speed effect whenever the player acquires it, but speed won't be replaced by those effects.
You can make it two-way by making both lists identical.

### You can name it anything, as long as it follows these rules:
1- use letters and numbers

2- No capitalization

3- No spaces (use an underscore `_` instead)