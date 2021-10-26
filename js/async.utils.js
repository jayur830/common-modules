export default class AsyncUtils {
    /**
     * @author Lee Jae Yeol
     * @param api: Function
     * @description
     * - 브라우저별로 제공하는 스레드를 이용한 비동기 작업 병렬 처리
     * */
    static async exec(...callbacks) {
        let count = 0;
        for (let i = 0; i < callbacks.length; ++i)
            (async () => {
                try {
                    await callbacks[i]();
                } catch (e) {e;} finally {
                    ++count;
                }
            })();
        while (count !== callbacks.length)
            await new Promise(resolve => setTimeout(resolve, 10));
    }
}
