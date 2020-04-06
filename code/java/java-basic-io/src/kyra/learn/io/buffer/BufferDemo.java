package kyra.learn.io.buffer;

import java.nio.ByteBuffer;

/**
 * @author liutiantian
 * @create 2020-04-06 15:58
 */
public class BufferDemo {
    public static void main(String[] args) {
        String s = "Hello World!";
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // MethodTest(s, buffer);
        buffer.put(s.getBytes());
        buffer.flip();
        byte[] dst = new byte[buffer.limit()];
        buffer.get(dst, 0, 2);
        System.out.println(buffer.position());

        // mark 标记
        buffer.mark();
        buffer.get(dst, 2, 3);
        System.out.println(buffer.position());

        buffer.reset();
        System.out.println(buffer.position());

        if (buffer.hasRemaining()){
            System.out.println(buffer.remaining());
        }

    }

    private static void MethodTest(String s, ByteBuffer buffer) {
        // 1. 为缓冲区分配指定大小的空间
        System.out.println("=========== allocate ==========");
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        // 2. Put 将数据存入缓冲区
        buffer.put(s.getBytes());
        System.out.println("=========== put 数据后 ==========");
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        // 3. 切换到读取数据模式
        buffer.flip();
        System.out.println("=========== flip() 后 ==========");
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        // 4. 利用 get 方法读取缓冲区的数据
        byte[] dst = new byte[buffer.limit()];
        buffer.get(dst);
        System.out.println(new String(dst));

        System.out.println("=========== get() 后 ==========");
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        // 5. rewind 可重复读数据
        buffer.rewind();

        System.out.println("=========== rewind() 后 ==========");
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        // 6. 清空缓冲区
        buffer.clear();
        System.out.println("=========== clear() 后 ==========");
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
    }
}
