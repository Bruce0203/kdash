let mapFile = `maps/map.json`
new PIXI.Loader()
.add(`maps/map.json`)
.load((file) => {
    file.resources[mapFile]
})