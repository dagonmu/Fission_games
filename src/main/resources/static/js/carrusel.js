
window.onload = function () {
    let slides =
        document.getElementsByClassName('1 carousel-item');
    let slides2 =
            document.getElementsByClassName('2 carousel-item');


    function addActive(slide) {
        slide.classList.add('active');
    }

    function removeActive(slide) {
        slide.classList.remove('active');
    }

    addActive(slides[0]);
    setInterval(function () {
        for (let i = 0; i < slides.length; i++) {
            if (i + 1 == slides.length) {
                addActive(slides[0]);
                setTimeout(removeActive, 350, slides[i]);
                break;
            }
            if (slides[i].classList.contains('active')) {
                setTimeout(removeActive, 350, slides[i]);
                addActive(slides[i + 1]);
                break;
            }

        }
        for (let i = 0; i < slides2.length; i++) {
                        if (i + 1 == slides2.length) {
                            addActive(slides2[0]);
                            setTimeout(removeActive, 350, slides2[i]);
                            break;
                        }
                        if (slides2[i].classList.contains('active')) {
                            setTimeout(removeActive, 350, slides2[i]);
                            addActive(slides2[i + 1]);
                            break;
                        }
                    }
    }, 5000);
};
