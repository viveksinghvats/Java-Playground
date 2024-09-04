const func1 = async () => {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            resolve("a");
            console.log('a');
        }, 1000);
    });
}

const func2 = async () => {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            resolve("b");
            console.log('b');
        }, 0);
    });
}

const func3 = async () => {
    return new Promise((resolve, reject) => {
        resolve("c");
        console.log('c');
    });
}

    await func1();
    await func2();
    await func3();




/*
a
*/