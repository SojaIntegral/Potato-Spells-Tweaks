
# To make so an effect won't apply when others are active, you will need to:

1- Create a list (example: `example.json`) in the `cannot_overlap` folder 

2- Create a list with THE SAME NAME, `example.json`, in the `active_effects` folder

## How an `exaple.json` would look like:

Inside `cannot_overlap` folder:
```json
{
  "replace": false,
  "values": [
    "minecraft:speed"
  ]
}
```
Inside `active_effects` folder:
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

Any effects in the "cannot overlap" won't be applied whenever an effect from the "active effects" is present.
You can make it two-way by making both lists identical, but that will make so refreshing effects before they end is impossible.

### You can name it anything, as long as it follows these rules:
1- use letters and numbers

2- No capitalization

3- No spaces (use an underscore `_` instead)