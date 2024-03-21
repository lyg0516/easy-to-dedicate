function fetchApi(endpoint, method = 'GET', body = null) {
  if (endpoint[0] !== '/') {
    endpoint = `/${endpoint}`;
  }
  const url = `/api${endpoint}`;
  const headers = { 'Content-Type': 'application/json' };
  const config = { method, headers };
  if (body !== null) {
    config['body'] = JSON.stringify(body)
  }

  return fetch(url, config)
    .then(async (res) => {
      const statusCode = res.status;

      if (res.ok) {
        // toasts.createToast('info', `Success Request '${url}'`);
        if (res.status !== 204) {
          return await res.json();
        }
      } else {

        return { statusCode: statusCode };
      }
    })
    .catch((error) => {

    });
}
