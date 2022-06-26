document.addEventListener(START, () => {
    newAnim("kat", (anim) => {
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
    })
    newAnim("test", (anim) => {
        anim.x = app.screen.width * (30/40);
        anim.y = app.screen.height * (27/40);
        anim.anchor.set(0.5);
        anim.animationSpeed = 0.2;
        anim.play();
        group = new PIXI.Container();
        app.stage.addChild(group)
        group.addChild(anim);

        // Animate the rotation
        app.ticker.add(() => {
        });
    })

})

