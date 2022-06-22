document.addEventListener(ON_ENABLE, () => {   //underBlock
    const underedBlock = new PIXI.Graphics();
    underedBlock.beginFill(0x3AB0FF);
    underedBlock.drawRect(0, height*1.5, width*2, height*2);
    underedBlock.endFill();
    center(underedBlock)
    app.stage.addChild(underedBlock);

    app.ticker.add(() => {
    })

})
