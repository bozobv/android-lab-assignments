override fun render(canvas: Canvas) {
    setSpriteSizes()

    val statePos = state % 4
    // 4 states, each image is 64*29

    val x = 0
    val y = spriteHeight * statePos

    val src = Rect(x, y, x + spriteWidth, y + spriteHeight)
    val dst = Rect(posX, posY, posX + spriteWidth * 4, posY + spriteHeight * 4)

    canvas.drawBitmap(image, src, dst, null)
}
