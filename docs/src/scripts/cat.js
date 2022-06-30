var cat = document.addEventListener(START, () => {
    newAnim("kat", (anim) => {
        anim.x = app.screen.width * (6/40);
        anim.y = app.screen.height * (17/40);
        anim.anchor.set(0.5);
        anim.animationSpeed = map.speed * 100
        anim.play();
        group = new PIXI.Container();
        app.stage.addChild(group)
        group.addChild(anim);

        // Animate the rotation
        app.ticker.add(() => {
        });
    })
    // newAnim("heartbeat2", (anim) => {
    //     anim.x = app.screen.width * (20/40);
    //     anim.y = app.screen.height * (20/40);
    //     anim.anchor.set(0.5);
    //     anim.animationSpeed = 0.25;
    //     anim.play();
    //     group = new PIXI.Container();
    //     app.stage.addChild(group)
    //     group.addChild(anim);

    //     // Animate the rotation
    //     app.ticker.add(() => {
    //     });
    // })
})
