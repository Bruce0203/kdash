document.addEventListener(START, () => {
    app.loader
    .add('assets/kat.json')
    .load(onAssetsLoaded);
})

function onAssetsLoaded() {
    // create an array of textures from an image path
    const frames = [];

    for (let i = 0; i < 4; i++) {
        const val = i < 4 ? `${i}` : i;

        // magically works since the spritesheet was loaded with the pixi loader
        frames.push(PIXI.Texture.from(`kat${val}.png`));
    }

    // create an AnimatedSprite (brings back memories from the days of Flash, right ?)
    const anim = new PIXI.AnimatedSprite(frames);

    /*
     * An AnimatedSprite inherits all the properties of a PIXI sprite
     * so you can change its position, its anchor, mask it, etc
     */
    anim.x = app.screen.width * (10/40);
    anim.y = app.screen.height * (17/40);
    anim.anchor.set(0.5);
    anim.animationSpeed = 0.2;
    anim.play();
    group = new PIXI.Container();
    app.stage.addChild(group)
    group.addChild(anim);

    // Animate the rotation
    app.ticker.add(() => {
    });
}
