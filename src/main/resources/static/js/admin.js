import {getClusters} from "./cluster_fetch.js";

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

document.addEventListener('DOMContentLoaded', async () => {
    const tbody = document.querySelector('.cluster-tbody');
    const noContent = document.querySelector('.no-content');

    const clusters = await getClusters();

    if (!clusters || clusters.length == 0) {
        console.log('클러스터가 없습니다!');
        return
    }

    noContent.style.display = 'none';
    clusters.forEach(cluster => {
        const tr = document.createElement('tr');

        const idTd = document.createElement('td');
        idTd.textContent = cluster['id'];
        tr.appendChild(idTd);

        const nameTd = document.createElement('td');
        idTd.textContent = cluster['name'];
        tr.appendChild(nameTd);

        const appTd = document.createElement('td');
        idTd.textContent = cluster['application_type'];
        tr.appendChild(appTd);

        const deployTd = document.createElement('td');
        idTd.textContent = cluster['deploy_type'];
        tr.appendChild(deployTd);

        const createTd = document.createElement('td');
        idTd.textContent = cluster['created_at'];
        tr.appendChild(createTd);

        //TODO 클러스터 상세 페이지
        // tr.addEventListener('click', () => {
        //     location.href = `/clusters/${cluster['id']}`;
        // })

        tbody.appendChild(tr);

    });



});

function createClusterFunc() {

}
