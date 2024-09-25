const getPerfumeInfo = () => {
    const perfumeId = window.location.href[window.location.href.length-1];
    fetch("/api/auth/perfumes/" + perfumeId)
    .then(json => json.json())
    .then(data => console.log(data))
}

export default getPerfumeInfo;