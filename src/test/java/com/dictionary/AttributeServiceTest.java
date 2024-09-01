package com.dictionary;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.dictionary.Repository.AttributeRepository;
import com.example.dictionary.model.Attribute;
import com.example.dictionary.service.AttributeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

public class AttributeServiceTest {
    //@Mock：标记 attributeRepository 为 Mockito 模拟对象。
    //Mockito会创建一个 AttributeRepository 的伪对象，用于测试中代替实际的数据库访问。
    @Mock
    private AttributeRepository attributeRepository;


    //@InjectMocks：标记 attributeService 为被测试的对象。Mockito 会自动将上面定义的模拟对象注入到这个对象中。
    @InjectMocks
    private AttributeService attributeService;


    //@BeforeEach：在每个测试方法之前执行。
    //setUp 方法用于初始化 Mockito 的注解（@Mock 和 @InjectMocks），这一步确保所有模拟对象和注入都已正确配置。
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUpdateAttributes() {
        Attribute attribute1 = new Attribute();
        attribute1.setId(1L);
        attribute1.setName("Attribute 1");
        attribute1.setName("属性 1");

        Attribute attribute2 = new Attribute();
        attribute2.setId(2L);
        attribute2.setName("Attribute 2");
        attribute2.setName("属性 1");


        List<Attribute> attributes = Arrays.asList(attribute1, attribute2);

        attributeService.updateAttributes(attributes);

        verify(attributeRepository, times(1)).saveAll(attributes);
    }

    @Test
    public void testGetAllAttributes() {
        Attribute attribute1 = new Attribute();
        attribute1.setId(1L);
        attribute1.setName("Attribute 1");
        attribute1.setName("属性 1");

        Attribute attribute2 = new Attribute();
        attribute2.setId(2L);
        attribute2.setName("Attribute 2");
        attribute2.setName("属性 1");
        //when(attributeRepository.findAll()).thenReturn(attributes)：指定当调用 findAll 方法时，返回预设的 attributes 列表。
        //调用 attributeService.getAllAttributes() 方法，并将结果与预期的 attributes 列表进行比较，确保 getAllAttributes 方法能够正确返回所有属性。
        List<Attribute> attributes = Arrays.asList(attribute1, attribute2);

        when(attributeRepository.findAll()).thenReturn(attributes);

        List<Attribute> result = attributeService.getAllAttributes();

        assertEquals(attributes, result);
    }

    @Test
    public void testGetAttributeNames() {
        Attribute attribute1 = new Attribute();
        attribute1.setId(1L);
        attribute1.setName("Attribute 1");
        attribute1.setName("属性 1");

        Attribute attribute2 = new Attribute();
        attribute2.setId(2L);
        attribute2.setName("Attribute 2");
        attribute2.setName("属性 1");

        List<Attribute> attributes = Arrays.asList(attribute1, attribute2);

        when(attributeRepository.findAll()).thenReturn(attributes);

        List<String> expectedNames = Arrays.asList("Attribute 1", "Attribute 2");
        List<String> result = attributeService.getAttributeNames();

        assertEquals(expectedNames, result);
    }
}
