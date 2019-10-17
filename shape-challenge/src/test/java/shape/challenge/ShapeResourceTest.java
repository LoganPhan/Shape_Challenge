package shape.challenge;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.shape.service.ShapeService;
import com.shape.service.dto.Attribute;
import com.shape.service.dto.Formular;
import com.shape.service.dto.Shape;
import com.shape.ws.rest.ShapeResource;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = ShapeResource.class)
public class ShapeResourceTest {
	
	protected static final Long DEFAULT_SHAPE_ID = 1l;
	

	protected static final String DEFAULT_DIAGONAL_1_ATTRIBUTE = "DIAGONAL_1";
	protected static final String DEFAULT_DIAGONAL_1_ATTRIBUTE_VALUE = "5.00";
	protected static final String DEFAULT_DIAGONAL_2_ATTRIBUTE = "DIAGONAL_2";
	protected static final String DEFAULT_DIAGONAL_2_ATTRIBUTE_VALUE = "10.00";
	protected static final String DEFAULT_DATA_TYPE = "DECIMAL";
	protected static final String DEFAULT_FORMULAR_AREA_NAME = "AREA";
	protected static final String DEFAULT_SHAPE_NAME = "RHOMBUS";
	protected static final String DEFAULT_RHOMBUS_AREA_FORMULAR_DIAGONAL_EXPRESSION = "(" + DEFAULT_DIAGONAL_1_ATTRIBUTE
			+ " * " + DEFAULT_DIAGONAL_2_ATTRIBUTE + ") / 2";// area = (d1 * d2)/2
	protected static final String DEFAULT_RHOMBUS_AREA_FORMULAR_DIAGONAL_VALUE = "25";
	
	protected static final String DEFAULT_WIDTH_ATTRIBUTE = "WIDTH";
	protected static final String DEFAULT_LENGTH_ATTRIBUTE = "LENGTH";
	
	@Autowired
    private MockMvc restShapeMockMvc;
    
	@Mock
	private ShapeService shapeService;
    
	@Mock
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;
    
    private Shape shape;
    
    private Shape initShape() {
   	 Set<Attribute> attributes = new LinkedHashSet<>();
        attributes.add(Attribute.builder().name(DEFAULT_DIAGONAL_1_ATTRIBUTE).dataType(DEFAULT_DATA_TYPE).required(Boolean.TRUE).build());
        attributes.add(Attribute.builder().name(DEFAULT_DIAGONAL_2_ATTRIBUTE).dataType(DEFAULT_DATA_TYPE).required(Boolean.TRUE).build());
        Set<Formular> formulars = new LinkedHashSet<>();
        formulars.add(Formular.builder().name(DEFAULT_FORMULAR_AREA_NAME).pattern(DEFAULT_RHOMBUS_AREA_FORMULAR_DIAGONAL_EXPRESSION).build());
       return  Shape.builder()
				.id(DEFAULT_SHAPE_ID)
		 		.name(DEFAULT_SHAPE_NAME)
		 		.attributes(attributes)
		 		.formulars(formulars).build();
   }
    
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ShapeResource shapeResource = new ShapeResource();
        ReflectionTestUtils.setField(shapeResource, "shapeService", shapeService);
        this.restShapeMockMvc = MockMvcBuilders.standaloneSetup(shapeResource).setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
    	shapeService.deleteAll();
    	shape = initShape();
    }

    @Test
    public void createShape() throws Exception {
        int shapeSize = shapeService.getShapes().size();

        // Create the Shape
        restShapeMockMvc.perform(post("/api/shapes")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(TestUtil.convertObjectToJson(shape)))
	            .andExpect(status().isCreated());

        // Validate the Shape in the ShapeReposistory
        List<Shape> shapes = shapeService.getShapes().stream().collect(Collectors.toList());
        assertThat(shapes).hasSize(shapeSize + 1);
        
        Shape testShape = shapes.get(shapes.size() - 1);
        assertThat(testShape.getId()).isEqualTo(DEFAULT_SHAPE_ID);
        assertThat(testShape.getName()).isEqualTo(DEFAULT_SHAPE_NAME);
        assertThat(testShape.getAttributes().size()).isEqualTo(shape.getAttributes().size());
        assertThat(testShape.getFormulars().size()).isEqualTo(shape.getFormulars().size());

    }

    @Test
    public void createShapeWithExistingId() throws Exception {
    	 int shapeSize = shapeService.getShapes().size();

        // Create the Shape with an existing ID
        Shape existingShape = new Shape();
        existingShape.setId(DEFAULT_SHAPE_ID);

        // An entity with an existing ID cannot be created, so this API call must fail
        restShapeMockMvc.perform(post("/api/shapes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJson(existingShape)))
            .andExpect(status().isBadRequest());

        // Validate the Default Shape Id in the database
        List<Shape> shapes = shapeService.getShapes();
        assertThat(shapes).hasSize(shapeSize);
    }

    @Test
    public void getShape() throws Exception {
    	Shape sha = shapeService.save(shape);
     
      // Get the bcAccount
    	restShapeMockMvc.perform(get("/api/shapes/{id}", sha.getId()))
          .andExpect(status().isOk())
          .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
          .andExpect(jsonPath("$.id").value(sha.getId().intValue()))
          .andExpect(jsonPath("$.name").value(DEFAULT_SHAPE_NAME));
    }
    
    @Test
    public void submitShape() throws Exception {
    	Shape submitShape = new Shape();
    	submitShape.setId(DEFAULT_SHAPE_ID);
    	Set<Attribute> attributes = new LinkedHashSet<>();
    	attributes.add(Attribute.builder().name(DEFAULT_DIAGONAL_1_ATTRIBUTE).value(DEFAULT_DIAGONAL_1_ATTRIBUTE_VALUE).build());
    	attributes.add(Attribute.builder().name(DEFAULT_DIAGONAL_2_ATTRIBUTE).value(DEFAULT_DIAGONAL_2_ATTRIBUTE_VALUE).build());
    	
    	// Create the Shape
        restShapeMockMvc.perform(post("/api/shapes/submit")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(submitShape)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(submitShape.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_SHAPE_NAME))
            .andExpect(jsonPath("$.formular[0].name").value(DEFAULT_FORMULAR_AREA_NAME))
            .andExpect(jsonPath("$.formular[0].value").value(DEFAULT_RHOMBUS_AREA_FORMULAR_DIAGONAL_VALUE))
            ;

    }
    
    @After
    public void remove() {
    	shapeService.deleteAll();
    	shape = null;
    }
    
   
}
