import tensorflow as tf

target_accuracy = 0.90

class trainCB(tf.keras.callbacks.Callback):
    def on_epoch_end(self, epoch, logs={}):
        if(logs.get('accuracy')>target_accuracy):
            print("\nReached " + str(target_accuracy*100) + "% accuracy, training stopped")
            self.model.stop_training = True

callbacks = trainCB()
data = tf.keras.datasets.fashion_mnist

(training_images, training_labels),(test_images, test_labels) = data.load_data()

training_images = training_images / 255.0
test_images = test_images / 255.0

training_images = training_images
training_labels = training_labels

model = tf.keras.models.Sequential([
    tf.keras.layers.Flatten(input_shape=(28,28)),
    tf.keras.layers.Dense(128, activation=tf.nn.relu),
    tf.keras.layers.Dense(10, activation=tf.nn.softmax)
])

model.compile(optimizer='adam',
              loss='sparse_categorical_crossentropy',
              metrics=['accuracy'])

model.fit(training_images, training_labels, epochs=100, callbacks=[callbacks])
model.evaluate(test_images, test_labels)
