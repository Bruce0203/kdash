function center(sprite) {
    sprite.pivot.x = sprite.width/2
    sprite.pivot.y = sprite.height/2
}

function newAnim(name, callback) {
    const frames = [];
    new PIXI.Loader()
    .add(`assets/${name}.json`)
    .load((file) => {
        console.log(file.resources[`assets/${name}.json`].data)
        let length = Object.keys(file.resources[`assets/${name}.json`].data.frames).length
        for (let i = 0; i < length; i++) {
            const val = i < length ? `${i}` : i;
            // magically works since the spritesheet was loaded with the pixi loader
            frames.push(PIXI.Texture.from(`${name}${val}.png`));
        }
        const anim = new PIXI.AnimatedSprite(frames);
        callback(anim)
        app.loader.reset()
    });
}