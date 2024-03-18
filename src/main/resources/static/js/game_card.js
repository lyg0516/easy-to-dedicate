document.addEventListener('DOMContentLoaded', function() {
    const container = document.getElementById('cards-container');
    const template = document.getElementById('game-card-template').content;



    // fetch('/api/games') // 게임 데이터를 제공하는 API
    //     .then(response => response.json())
    //     .then(data => {
    //         const container = document.getElementById('cards-container');
    //         const template = document.getElementById('game-card-template').content;
    //
    //         data.forEach(game => {
    //             const clone = document.importNode(template, true);
    //
    //             const heroImage = clone.querySelector('.hero-image');
    //             heroImage.src = game.imageUrl;
    //             heroImage.alt = 'Game image';
    //
    //             const gameNameLink = clone.querySelector('main .main-content h1 a');
    //             gameNameLink.textContent = game.name;
    //
    //             const gameDescription = clone.querySelector('main .main-content p');
    //             gameDescription.textContent = game.description;
    //
    //             // clone에 있는 나머지 데이터도 마찬가지로 업데이트
    //
    //             container.appendChild(clone);
    //         });
    //     });
});
