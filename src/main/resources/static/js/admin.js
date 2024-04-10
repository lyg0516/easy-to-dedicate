

document.querySelector('.cluster-plus')
    .addEventListener('click',() => {
        console.log('Plus Click!');
        const modal = document.querySelector('.modal-area');
        modal.style.display = 'flex';
        modal.querySelector('.window__btn')
            .addEventListener('click', () => {
               modal.style.display = 'none';
            });

    });
